package javaPro_lec08_0421;
/*
<슬라이드9>
파일 훝어보고 감잡기
20~30년치 전 종목 주가데이터를 받아왔다. 이 파일은 데이터베이스를 덤프받은 파일이다. 데이터베이스 구조파일도 받아서 대충 구조도 알듯하다
(애석하게 최신 주가데이터는 유료정보라 교수님이 어찌어찌 2016년까지 실습용으로 모아 놓은 것이니 절대 외부 반출 금지임)
크기가 너무 커서 메모장 등은 읽어지지도 않는다. 
하나씩 읽어보고 찍어보고 감잡아 본다
음..한줄씩 데이터가 쓰여 있고, 구분자로 분리된 파일이다.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class p09_filePractice_Method {
	public static void main(String[] args) throws IOException {
		// 처리전 시간 획득
		long k38_start = System.currentTimeMillis();
		System.out.print("시작시간 : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(k38_start));
				
		k38_FileRead();		//메서드 호출
				
		// 처리후 시간 획득
		long k38_end = System.currentTimeMillis();
		System.out.print("종료 후 시간 : ");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(k38_end));
		
		// 시간차이 출력_숫자입력까지 실제로 걸린시간
		System.out.println("소요시간 : " + (k38_end - k38_start) / 1000.0 + " seconds\n");
	}
	
	//메서드 : 파일읽기
	public static void k38_FileRead() throws IOException {
		//실습용 주가정보 데이터가 있는 파일을 불러온다.
		File k38_f = new File ("C:\\Users\\kopo\\Desktop\\javaProTest\\THTSKS010H00.dat");
		BufferedReader k38_br = new BufferedReader(new FileReader(k38_f));  // k38_f파일 BufferedReader 객체 생성해 읽기
		
		String k38_readtxt; 	// 문자열 k38_readtxt 정의, 이 클래스에서 미사용 
		
		int k38_cnt = 0;	//총데이터 개수를 담을 정수형 변수 선언 및 값 초기화	=> 읽은 횟수 카운트
		
		int k38_Linecnt = 0;	//변수 선언 및 값 초기화, 이 클래스에서 미사용
		int k38_n = -1; 		//0보다 작을때의 범위를 고려해서 0보다 작은 값인, -1로 값을 initiate해줌
		StringBuffer k38_s = new StringBuffer(); 	//StringBuffer : 문자열을 추가하거나 변경 할 때 주로 사용하는 자료형
		
		//조건 참이면 계속 반복
		while(true) {
			char[] k38_ch = new char[1000];  //크기 1000의 char배열 생성
			k38_n = k38_br.read(k38_ch); //k38_ch의 char배열 읽은 결과를 변수 k38_n에 저장 &&  \n도 1로 카운트
			
			//k38_n 이 -1되면 읽을 내용이 없다는 뜻
			//.read 는 읽을 값이 없으면 -1을 반환한다.
			if (k38_n == -1) //k38_n값이 -1이면
				break; 		 //while 종료
			
			//forecah문 => for([타입] [값받아줄 변수명] : 출력하고 싶은 자료구조(=배열))
			//해당배열의 요소를 모두 출력함.
			//배열 k38_ch 의 요소를 모두 출력.
			for (char k38_c : k38_ch) {
				if(k38_c == '\n') { //char가 줄바꿈이면
					System.out.printf("[%s]***\n", k38_s.toString()); //StringBuffer로 toString() 메서드 호출 => String 객체로 변환
					k38_s.delete(0, k38_s.length()); //추가된 버퍼들 0부터 끝까지(k38_s.length()) 전부삭제
				} else {	//줄바꿈 아니면
					k38_s.append(k38_c);	//k38_s의 끝에 k38_c 내용 추가
				}
			}
			k38_Linecnt++;//줄 수가 하나씩 증가
		}	//while
		System.out.printf("[%s]***\n", k38_s.toString());	 //StringBuffer로 toString() 메서드 호출 => String 객체로 변환
		k38_br.close();//버퍼리더 닫아주기 - Close를 안하면 계속 파일이 열려있어서 제어할 수 가 없다			
	}
}


/*
출력결과 예>
[KR7032620007^%_%^20120118^%_%^A032620  ^%_%^4280^%_%^4360^%_%^4650^%_%^4265^%_%^2^%_%^80^%_%^1.9^%_%^2438681^%_%^9050668^%_%^40369303620^%_%^4285^%_%^4280^%_%^24304^%_%^102137^%_%^4274838^%_%^4364922^%_%^^%_%^^%_%^7608^%_%^12802^%_%^4840^%_%^4055^%_%^20120110^%_%^20120109^%_%^180000^%_%^4280^%_%^0^%_%^3^%_%^4285^%_%^4280^%_%^51985^%_%^222434460^%_%^4280^%_%^4280^%_%^4270^%_%^50^%_%^210000^%_%^30167^%_%^129114760^%_%^4^%_%^^%_%^40286470^%_%^4200^%_%^500^%_%^4460.97194696806^%_%^100^%_%^40286470^%_%^39684850^%_%^0^%_%^601620^%_%^1.493355^%_%^1.493355^%_%^0^%_%^0^%_%^0^%_%^0^%_%^129631^%_%^584296430^%_%^16609^%_%^00^%_%^1^%_%^1^%_%^8.17^%_%^3.574998^%_%^^%_%^^%_%^^%_%^^%_%^^%_%^^%_%^^%_%^4830^%_%^3570^%_%^00^%_%^8968466^%_%^40017544400^%_%^00^%_%^00^%_%^0^%_%^4280^%_%^4360^%_%^4650^%_%^4265^%_%^4280^%_%^9050668^%_%^40369303620^%_%^1^%_%^00^%_%^^%_%^^%_%^^%_%^^%_%^^%_%^^%_%^^%_%^]***
[_]***
[KR7032640005^%_%^20120118^%_%^A032640  ^%_%^6610^%_%^6790^%_%^6850^%_%^6570^%_%^5^%_%^-170^%_%^-2.51^%_%^2046815^%_%^2268108^%_%^15183191770^%_%^6620^%_%^6610^%_%^188780^%_%^312630^%_%^1485266^%_%^587640^%_%^^%_%^^%_%^3530^%_%^2231^%_%^7130^%_%^6510^%_%^20120105^%_%^20120112^%_%^180000^%_%^6630^%_%^20^%_%^2^%_%^6630^%_%^6620^%_%^9028^%_%^59779740^%_%^6610^%_%^6630^%_%^6610^%_%^89^%_%^603420^%_%^10211^%_%^67494710^%_%^1^%_%^^%_%^514793835^%_%^6780^%_%^5000^%_%^6694.58768859^%_%^49^%_%^252248979^%_%^154085311^%_%^0^%_%^98163668^%_%^19.06854^%_%^38.91539^%_%^0^%_%^0^%_%^0^%_%^0^%_%^132320^%_%^877968400^%_%^-178280^%_%^00^%_%^1^%_%^1^%_%^0.52^%_%^0.1291425^%_%^^%_%^^%_%^^%_%^^%_%^^%_%^^%_%^^%_%^7790^%_%^5770^%_%^00^%_%^2248780^%_%^15055313900^%_%^00^%_%^00^%_%^-460090^%_%^6610^%_%^6790^%_%^6850^%_%^6570^%_%^6610^%_%^2268108^%_%^15183191770^%_%^1^%_%^00^%_%^^%_%^^%_%^^%_%^^%_%^^%_%^^%_%^^%_%^]***
[_]***
*/