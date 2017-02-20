package collectionFramework;

import java.util.*;

//simulate Web browser's back and forward button using stack
public class BackForwardStack {
	public static Stack back = new Stack();
	public static Stack forward = new Stack();
	
	public static void main(String[] args) {
		goURL("google.com");
		goURL("naver.com");
		goURL("twitter.com");
		goURL("facebook.com");

		printStatus();
		goBack();
		printStatus();
		goBack();
		printStatus();
		goForward();
		printStatus();
	}

	public static void printStatus(){
		System.out.println("Current web : "+back.peek());
		System.out.println("back stack : "+back);
		System.out.println("forward stack : "+forward);
	}

	public static void goURL(String url){	//해당 url로 이동하고 forward를 초기화 함
		back.push(url);
		if(!forward.empty())
			forward.clear();
	}
	
	public static void goForward(){
		System.out.println("\n---Moved to forward page---");
		if(!forward.empty())
			back.push(forward.pop());
	}
	
	public static void goBack(){
		System.out.println("\n---Moved to back page---");
		if(!back.empty())
			forward.push(back.pop());
	}
}