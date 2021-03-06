# jquery.confirm

Confirm dialogs for buttons and links.

Chat room: [![](https://badges.gitter.im/myclabs/jquery.confirm.png)](https://gitter.im/myclabs/jquery.confirm)

## Requirements

- jQuery > 1.8
- [Bootstrap](http://twitter.github.com/bootstrap/) 3 for the modals

If you use Bootstrap 2, you can use any 1.x version. From 2.0 and onwards, Bootstrap 3 is required.

## Installation

The library can be downloaded manually or installed with Bower:

```
$ bower install jquery-confirm
```

## Usage

```html
<a href="home" class="confirm">Go to home</a>
```

```javascript
$(".confirm").confirm();
```

Any click on the link will pop up a dialog asking the user to confirm the action.

## Options

List of the options:

* `text`: Text to display in the dialog
* `title`: Title of the dialog (can be empty, the dialog will not have a header then)
* `confirm`: Handler executed when the user confirms
* `cancel`: Handler executed when the user cancels
* `confirmButton`: Label of the confirm button
* `cancelButton`: Label of the cancel button
* `post`: If false (default) and no confirm handler is set, redirects the user to the URL of the button/link with a GET request. If true, redirects with a POST request (like a form submission).
* `confirmButtonClass`: HTML class for the confirmation button, defaults to `btn-primary`.

Example:

```html
<button class="confirm" type="button">Delete the comment</button>
```

```javascript
$(".confirm").confirm({
    text: "Are you sure you want to delete that comment?",
    title: "Confirmation required",
    confirm: function(button) {
        delete();
    },
    cancel: function(button) {
        // nothing to do
    },
    confirmButton: "Yes I am",
    cancelButton: "No",
    post: true,
    confirmButtonClass: "btn-danger"
});
```

You can also use `data-` attributes to customize the confirmation dialog:

```html
<button class="confirm" type="button"
        data-text="Do you really want to delete that comment?"
        data-confirm-button="Yes I am">
    Delete the user
</button>
```

```javascript
$(".confirm").confirm();
```

## Manual triggering

You can manually trigger the confirmation dialog:

```javascript
// Will immediately show the confirmation popup
$.confirm({
    text: "Are you sure you want to delete that comment?",
    confirm: function() {
        delete();
    },
    cancel: function() {
        // nothing to do
    }
});
```

Remember that if you trigger the confirmation dialog manually, you must provide a `confirm` callback (as shown above).

## Global configuration

You can configure some options globally, for example if you translate the messages:

```javascript
$.confirm.options = {
    text: "Are you sure?",
    title: "",
    confirmButton: "Yes",
    cancelButton: "Cancel",
    post: false,
    confirmButtonClass: "btn-warning"
}
```

## Change log

* **2.2.0** Use `data-*` attributes to customize the dialog + change the confirm button HTML class (thanks to [@MarcusSchwarz](https://github.com/MarcusSchwarz)) + bugfix
* **2.1.0** [Global options configuration](https://github.com/myclabs/jquery.confirm/pull/16) (thanks to [@soulman-is-good](https://github.com/soulman-is-good))
* **2.0.0** Bootstrap 3 compatibility (thanks to @RusselVela)
* **1.3.0** Minified version and specific modal CSS class (thanks to @mrjoelkemp)
* **1.2.0** Bugfix when `confirm()` is applied to a collection of links
* **1.1.0** Trigger the confirm dialog manually with `$.confirm()`
* **1.0.0** First release
