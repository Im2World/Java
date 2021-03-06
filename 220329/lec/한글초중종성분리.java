package lecture0329;

import java.util.Scanner;
import java.util.*;
import java.lang.*;

public class 한글초중종성분리 {
	// 한글자모 유니코드값
	// 초성 19개
	public static char[] arrChoSung = { 0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 0x3142, 0x3143, 0x3145,
			0x3146, 0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };
	// 중성 21개
	public static char[] arrJungSung = { 0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158,
			0x3159, 0x315a, 0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162, 0x3163 };
	// 종성 28개
	public static char[] arrJongSung = { 0x0000, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136, 0x3137, 0x3139, 0x313a,
			0x313b, 0x313c, 0x313d, 0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145, 0x3146, 0x3147, 0x3148,
			0x314a, 0x314b, 0x314c, 0x314d, 0x314e };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String word; // 쪼갤단어
		String result = ""; // 결과 저장할 변수, 최초상태는 초기화
		word = sc.nextLine();

		// word string글자 수 만큼 list에 담음 , 배열 크기4면, 인덱스는0123
		for (int i = 0; i < word.length(); i++) {
			// 한글자씩 읽어들이기
			char chars = (char) (word.charAt(i) - 0xAC00);
			if (chars >= 0 && chars <= 11172) { // 자음모음 합쳐진 글자인 경우
				// 1.분리
				int cho = chars / (21 * 28);
				int jung = chars % (21 * 28) / 28;
				int jong = chars % (21 * 28) % 28;

				// 2.분리내용 result 담기
				result = result + arrChoSung[cho] + arrJungSung[jung];

				// 3.자음 ㅣㅑㅓㅕ 분리
				if (jong != 0x0000) {
					result = result + arrJongSung[jong];
				}
			} else { // 한글아니거나 자음만 있는 경우
				// 4. 자음분리
				result = result + ((char) (chars + 0xAC00));
			}
		} // line36 for close

		System.out.println("===========result===========");
		System.out.println(word);
		System.out.println(result);
	}
}

/*
 * 유니코드 한글은 0xAC00 으로부터 초성 19개, 중상21개, 종성28개로 이루어지고 이들을 조합한 11,172개의 문자를 갖는다.
 * 한글코드의 값 = ((초성 * 21) + 중성) * 28 + 종성 + 0xAC00 (0xAC00은 ‘ㄱ'의 코드값)
 */
