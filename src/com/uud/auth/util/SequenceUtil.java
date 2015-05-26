package com.uud.auth.util;

import java.util.concurrent.atomic.AtomicInteger;

public class SequenceUtil {
	
	private static AtomicInteger sequence=new AtomicInteger(1);
	private static long now;

	public static void reset(){
		sequence.set(0);
	}
	
	public static String getSequence(){
		long current = System.currentTimeMillis();
		int s = 1;
		if( now == current ){
			s = sequence.incrementAndGet();	
		} else {
			now = current;
			sequence.set( 1 );
		}
		String str = String.valueOf( s );
		int len = 4 - str.length();
		if(  len > 0 ){
			for( int i=0;i<len ;i++ ){
				str = "0"+str;
			}
		}
		return  Long.toHexString( Long.parseLong( now + str ) ); 
	}
	
	public static void main( String args[] ){
    	for( int i =0 ;i < 1 ; i++){
			new Thread( new TestT() ).run();
		}
	}
}

class TestT implements Runnable{

	public void run() {
		System.out.println( SequenceUtil.getSequence() );
	}
	
}
