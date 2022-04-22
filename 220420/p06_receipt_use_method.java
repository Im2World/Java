package javaPro_lec06_self_ppt;
//�ڹٽ�ȭ 06�� _ ��6
//<����> �ڹٽ�ȭ 05���� ������3���� �޼��� ����� ����
//�����Լ����� ����ϴ� �޼���� �������� ���ǿ��� ������.
//getBytes().length ���� �� �޼��带 ���ڸ� 1���� �޾Ƶ� �ȴ�.
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class p06_receipt_use_method {
	///////////////////////////////////
	//1. ���� �� �迭 ����, �� ����
	//�޼���鿡�� ����ϱ����� ���������� ����
	static Calendar k38_calendar = Calendar.getInstance();		//Calendar.getInstance() �� ������ �������, �ú��� Ȯ��
	static SimpleDateFormat k38_sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");  		//Calendar�� �޾ƿ� ���� ���� ���� ����, SimpleDateFormat�� ������ %s�� �޴´�.
	static SimpleDateFormat k38_barcode_sdf = new SimpleDateFormat("YYYYMMdd"); 	   //���ڵ��ð� ǥ��, �����������Ϸ� ���
	//�ݾ׿� �޸� ���
	//DecimalFormat �� ���ڵ������� ��������
	static DecimalFormat k38_df = new DecimalFormat("###,###,###,###,###");   //3�ڸ�����(õ ������) �޸� ���
	
	//��ǰ �׸� �迭
	//��ǰ�� �迭�� String ���� �޴´�. 
	//��ǰ�� �Է� ���Ǹ� ���� 5������ �ٹٲ��ؼ� �ۼ� => �� 31�� �׸�
	static String[] k38_itemName = {"������â�� 2L", "����ũ�����¡�δ��", "��Ƽ��6��������", "�������θӽ���(Ư)", "�븸�Ļ�����ġ6����", //���ڿ��̶� String���� ����.
			"���ѱ� �������������� ġ��ũ����Ʈ ����460g", "Ǯ���� �뿧������ ���������̺�ġ�� 376g", "Ǯ����_ġ������_ġ���Ʈ���� ����_397g", "�ȵ������ 130g*4��", " [������]ȫ������ 40ml*20��",
			"[����ũ] ��ȫ�� ĵ�� 460g", "����ũ ����«���� 500g", "[����ũ]���� ���� ���ġŲ 750g", 	"����ũ ������ ���̽�Ĩ ����� 120g", 	"����ũ ���� ��ٷο�500g",
			"[����ũ]����� ��� ��붱 400g", "����ũ ���ڿ�������", "���絵 �������� ���Ű��", "�����Ұ����ڻ�������Ĩ", "���ϸ��� ����� ���� 500ml(125ml*4��)",
			"Ǫ���� �̼��������", "���Ϸν���������500g", "[����][����] ���� ������ (1kg/��)", "���� REAL���� 1.8kg/��", "�ĸӽ��� �絵������� 8~14��/�� (3kg)",
			"ũ������ȣ�� 25cm x 20m", "ũ���尩 100��(2+1)", "�ڵ� ����Ʈ 3�� ���� ȭ���� 30m*30��", "û��)�˵�����Ĩ240g", "[Űģ����] �����̾� ����ʹ�A 18��","������S22Ultra"}; 

	//�ܰ� �迭�� ������ �迭 => ��ǰ�� �迭 ������� ���� �Է�
	//�Է� ���Ǹ� ���� 5������ �ٹٲ��ؼ� �ۼ�
	static int[] k38_price = {540, 9980, 7280, 43000, 5900,
			6980, 6980, 9480, 2680, 46000,
			4980, 7920, 8386, 1980, 7480,
			7980, 4990, 26900, 1980, 4440,
			5960, 1990, 7980, 10900, 12900,
			2808, 9160, 9900, 4980, 17583, 1299900};	//6��

	//��ǰ ���� �迭�� ������ �迭 => ��ǰ�� �迭 ������� ���� �Է�
	static int[] k38_amount = {6, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1};

	//�鼼��ǰ ���� Ȯ������ �迭 => ***�鼼��ǰ�̸� true��.*** => �ΰ����� ���� �ʴ´�.
	//��깰, ������� �鼼��ǰ!
	//�� 31�� ��ǰ
	static boolean[] k38_taxFree = {false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, 
			false, false, true, false, true, false, false, true, true, true, false, false, false, false, false, false};			
	
	// ���� ���� �� ���� => �� �޼��忡�� Ȱ���ϱ����� static������ ����
	static int k38_taxFreeSum = 0; // �鼼��ǰ ������ (1�� ���� * �Ѱ���)
	static int k38_preTaxPriceSum = 0; // ������ǰ(�����ݾ�) ������ (1�� ���� * �Ѱ���)
	static int k38_allPriceSum = 0; // �鼼, ������ǰ ������
	static int k38_vat = 0; // �ΰ��� ���� ���� �� �� ����

	////////////////////////////////////////////
	//2. �����Լ�
	public static void main(String[] args) {
		int k38_itemCount = k38_itemName.length;	//������ ���� k38_itemCount�� k38_itemName ũ��� ����. 
		titlePrint();	//Ÿ��Ʋ�޼��� ȣ��
		timeStemp();	//Ÿ�ӽ����� �޼��� ȣ��
		headerPrint();	//��� �޼��� ȣ��
		for (int k38_i = 0; k38_i < k38_itemCount; k38_i++) {	//������ī��Ʈ ũ�⸸ŭ(=k38_itemName ���̸�ŭ) �ݺ�����
			itemPrint(k38_i);	//����������Ʈ �޼��� ȣ��=> ���ڷ�k38_i����
		}
		totalPrint();	//��Ż����Ʈ �޼��� ȣ��
	} // main

	//////////////////�޼����///////////////////////
	//3.������ �� �� Ÿ��Ʋ�μ� �޼���
	public static void titlePrint() {
		System.out.printf("%-13s%2s","", "�̸�Ʈ ������ (031)888-1234\n"); 	//������� �� �ٹٲ�
		System.out.printf("emart %-7s%2s","", "206-85-50913 ����\n"); 	//������� �� �ٹٲ�
		System.out.printf("%-13s%2s","", "���� ������ ������� 552\n"); 	//������� �� �ٹٲ�
		System.out.printf("\n������ �������� ��ȯ/ȯ�� �Ұ�\n"); 			//�ٹٲ� �� ������� �� �ٹٲ�
		System.out.printf("�����ǰ�� ����, 30�� �̳�(�ż� 7��)\n"); 			//������� �� �ٹٲ�
		System.out.printf("���Ϻ� �귣����� ����(���� ����������)\n"); 		//������� �� �ٹٲ�
		System.out.printf("��ȯ/ȯ�� ���������� ����(����ī�� ����)\n\n"); 	//������� �� �ٹٲ� 2��
	}
	
	//4.��½ð� �μ� �޼���
	public static void timeStemp() {
		System.out.printf("%-20s%18s\n","[�� ��]"+ k38_sdf.format(k38_calendar.getTime()),"POS:0011-9861");	//Calendar.getInstance() ���� �� �ð����� ���
	}
	
	//5.���(��ǰ��, �ܰ�, ����, �ݾ�)�μ� �޼���
	public static void headerPrint() {
		System.out.printf("-----------------------------------------\n");	//�ǽ��� ������ ���� �ʺ� 41ĭ�̴�=> ���м����� 41ĭ	
		System.out.printf("%-1s %-14s %-2s%3s%9s\n","", "�� ǰ ��", "�� ��","����","�� ��");	//�׸�� ������ ��� => ĭ�� ���� 41 		
		System.out.printf("-----------------------------------------\n");	//�ǽ��� ������ ���� �ʺ� 41ĭ�̴�=> ���м����� 41ĭ		
	}
	
	//6.�׸�(��ǰ��, �ܰ�, ����, �ݾ�)��� �޼���
	public static void itemPrint(int k38_i) {
		//�鼼��ǰ������
			if (k38_taxFree[k38_i] == true) {	//�鼼��ǰ�� ���
				k38_taxFreeSum += k38_price[k38_i] * k38_amount[k38_i];	//�ش� ���� * ���� �� ���� �ε��� ���� ��� ���Ѵ�.
	            System.out.printf("%-2s", "*");		//�鼼��ǰ �ڿ��� *��� ���, �������ķ� 2ĭ ����
			} else {	//�鼼��ǰ�ƴϸ� => ������ǰ�̸�
	            System.out.printf("%-2s", " ");		//������ǰ �ڿ��� �������ķ� ��ĭ 2ĭ����
			}
			k38_allPriceSum += k38_price[k38_i] * k38_amount[k38_i];	//����ǰ ������ 
			
	     //********�ٽɺ�********
		//��ǰ��, ���� ����, ����, �Ѿ� ���
		//��� ���ڿ��̶� %s�� �޾��ش�. k38_df.format�� ������ ���ڿ��� �޴´�.
        System.out.printf("%s %s", k38_subStrByte(k38_itemName[k38_i], 15), "");	//��ǰ�� ���
        System.out.printf("%9s %2s %10s\n", k38_df.format(k38_price[k38_i]), k38_amount[k38_i], k38_df.format(k38_price[k38_i] * k38_amount[k38_i]));	//���簡��, ����, �Ѿ�
       
        //***�ٽɺ�***
        //5�� ����� �Ǹ�(��ǰ5���� ��� ��) ���м� ��� �� �ٹٲ� => 5, 10, 15, 20, 25, 30
        	if ((k38_i + 1) % 5 == 0) {
        		System.out.printf("-----------------------------------------\n");	//�ǽ��� ������ ���� �ʺ� 41ĭ�̴�=> ���м����� 41ĭ
        	}
	}
	
	//7.�հ�, ���� �� �μ�
	public static void totalPrint() {						
		//������ǰ(�����ݾ�) �ݾ� ��� : (��ü�ݾ� - �鼼��ǰ�ݾ�) / 11 * 10
		//�Ǽ������� ����� �� ����ȯ�ؼ� �Ҽ������� ���� => �ΰ��� �ø�
		k38_preTaxPriceSum = (int)((k38_allPriceSum - k38_taxFreeSum) / 11.0 * 10.0);	//11.0, 10.0 ���� �Է��ؾ��Ѵ�!
		
		//�ΰ��� ��� : ��ü�ݾ� - �鼼�ݾ� - �����ݾ�
		k38_vat = k38_allPriceSum - k38_taxFreeSum - k38_preTaxPriceSum;
		
		//������ ��º�
		//�ݾ׿� DecimalFormat ������� õ������ �޸��� ������.
		System.out.printf("\n%-5s%17s%14s\n","", "�� ǰ�� ����", k38_itemName.length); //�� ǰ�����= k38_itemName �迭 ����
		System.out.printf("%-5s%18s%14s\n","", "(*)�� ��  �� ǰ", k38_df.format(k38_taxFreeSum)); // �鼼���� ��
		System.out.printf("%-5s%18s%14s\n","", "�� ��  �� ǰ", k38_df.format(k38_preTaxPriceSum));// ������ǰ��(�����ݾ�)
		System.out.printf("%-5s%19s%14s\n","", "��   ��   ��", k38_df.format(k38_vat));// �ΰ�����
		System.out.printf("%-5s%20s%14s\n","", "��        ��", k38_df.format(k38_allPriceSum));// �鼼������ + ������Ǯ�� + �ΰ��� = �հ�
		System.out.printf("�� �� �� �� �� �� %11s%12s\n","",k38_df.format(k38_allPriceSum));// �������ݾ��� �հ�� ����
		System.out.printf("-----------------------------------------\n");	//�ǽ��� ������ ���� �ʺ� 41ĭ�̴�=> ���м����� 41ĭ
		System.out.printf("%-16s%3s\n","0012 ���� ī��", "541707**0484/35860658");  //ī���̸� �� ���

		//�Ѿ� �ݾ� ������ ���� �Ѿ� ��¼���(�ڸ���) ����
		//printf ���Ŀ��� �������� �� + �� ����� �� ����Ʈ
		if(k38_df.format(k38_allPriceSum).length() == 9) {	//�޸� �������� �鸸�� ���� �ڸ����� 9�� �ȴ�.
			System.out.printf("%-19s%5s\n","ī�����(IC)", "�Ͻú� / " + k38_df.format(k38_allPriceSum));	//�鸸���������� ��� : %-19s
		} else if (k38_df.format(k38_allPriceSum).length() == 10) {	//�޸� �������� õ���� ���� �ڸ����� 10�̵ȴ�.
			System.out.printf("%-18s%5s\n","ī�����(IC)", "�Ͻú� / " + k38_df.format(k38_allPriceSum));	//õ������������ ��� : %-20s		
		}
		System.out.printf("-----------------------------------------\n");	//�ǽ��� ������ ���� �ʺ� 41ĭ�̴�=> ���м����� 41ĭ		
		System.out.printf("%-11s%s\n","","[�ż�������Ʈ ����]");  //�������� �������� ��ĭ ����� �������  
		System.out.println("��*�� �������� ����Ʈ ��Ȳ�Դϴ�.");	//�ȳ����� ���
		System.out.printf("%-13s%7s%11s\n","��ȸ�߻�����Ʈ", "9350**9995", k38_df.format((int)(k38_allPriceSum / 1000))); //�����ݾ��� 0.001% ����Ʈ����
		System.out.printf("%-13s%6s%12s%1s\n","����(����)����Ʈ", "999,860(", k38_df.format(999860-(int)(k38_allPriceSum / 1000)), ")"); //��������Ʈ = ��������Ʈ - ��ȸ�߻�����Ʈ 
		System.out.println("*�ż�������Ʈ ��ȿ�Ⱓ�� 2���Դϴ�.");	//�ȳ����� ���
		System.out.printf("-----------------------------------------\n");	//�ǽ��� ������ ���� �ʺ� 41ĭ�̴�=> ���м����� 41ĭ
		System.out.printf("%22s\n","���űݾױ��� ���������ð� �ڵ��ο�"); //����4ĭ + ����18�� = %22s
		System.out.printf("%-27s%9s\n", "������ȣ :","38��****");	//������ȣ ���Ƿ� �Է�
		System.out.printf("%-18s%9s\n", "�����ð� :", k38_sdf.format(k38_calendar.getTime()));	// �����ð��� �ǽð��ð����� �����.      
		System.out.printf("-----------------------------------------\n");	//�ǽ��� ������ ���� �ʺ� 41ĭ�̴�=> ���м����� 41ĭ
		System.out.printf("%-29s%9s\n", "ĳ��:084599 ��00","1150");	//�ȳ����� ���
		System.out.printf("%38s\n","|| ||| |||| |||  | |||||||||||| |||"); // ���ڵ� ���
		System.out.printf("%15s%s",(k38_barcode_sdf.format(k38_calendar.getTime())),"/00119861/00164980/31"); //���ڵ� ��ȣ: ���糯¥ + ������			
	}
	
	//////////////////////�ٽɺ�///////////////////////
	//8. ����Ʈ���� �ѱ� �ڸ���
	//String�� ��ȯ�ϴ� �Լ�����, ���ڷ� String�� int�� �޴´�.
	//k38_lengthLimit�� ���ڿ��� �ڸ��� ������ �Ǵ� ������
	public static String k38_subStrByte(String k38_str, int k38_lengthLimit) {	//String, int���� �ް�
		if (!k38_str.isEmpty()) {	//k38_str�� ���� �ƴ϶��
			k38_str = k38_str.trim();	//trim�޼��� : ���ڿ��� ���� ���ʰ���, ���� ������ ���� �����ϰ� �ٽ� k38_str�� ����
			if (k38_str.getBytes().length < k38_lengthLimit) {	//���ڷ� ���� k38_str�� ����Ʈ���� �ް�, ����Ʈ���� ���̰� k38_lengthLimit���� ������ => �������̶��
				//��ǰ�� �������� ������ ���� ���� �ش繮�ڿ� ���̰� ª���� �� ���̸�ŭ �����߰� => ��ǰ����, ���� ���� ������ġ ���Ե�.
				for (int k38_i = k38_lengthLimit - k38_str.getBytes().length; k38_i > 0; k38_i--) {
					k38_str += " ";		//������ �߰��Ѵ�.
				}				
				return k38_str;		//������ �߰��� ���� ��ȯ
			} else {
				//StringBuffer : ���ڿ� �߰�, �����ϴ� �޼���
				StringBuffer k38_sb = new StringBuffer(k38_lengthLimit);	//k38_sb��� ��ü����
				int k38_cnt = 0;	//���ڿ� ����üũ���� ���� ���� �� 0���� �� ����
				
				//foreach�� :  for(Ÿ�� ���޾��� ������ : ����ϰ� ���� �ڷᱸ��(=�迭)) =>�ε��� ǥ���ʿ���� �迭�� �� �����
				for (char k38_ch : k38_str.toCharArray()) {		//String k38_str�� �ѱ��ھ��߶�(toCharArray()) ��� ������.
					//String.valuOf : �Ķ���Ͱ� null�̸� null��ȯ, �׻� String���� ���
					k38_cnt += String.valueOf(k38_ch).getBytes().length;	//���ڷ� ���� String�� ����Ʈ���� ���, �ش����Ʈ�� ���̸� cnt�� ����
					if (k38_cnt > k38_lengthLimit)	//cnt�� ��ǰ�� ���ڿ� �Է�ĭ �ִ���̺���ũ��
						break;	//�ݺ��ߴ�
					k38_sb.append(k38_ch);	//append�� StringBuffer�� �� �߰��� �� ���.
											//=> k38_ch���� �߰��Ѵ�. => k38_ch�� ���ڷ� ���� String�� �ѱ��ھ��ɰ���
				}
				
				//��ǰ�� ĭ ���̸� 15ĭ���� �����ߴ�. ���� 14ĭ�̶�� ���鹮�ڸ� �Ѱ� �߰��ؼ� ��ġ�� �����Ѵ�.
				if (k38_sb.toString().getBytes().length == k38_lengthLimit - 1) {
					k38_sb.append(" ");	//���� ����1ĭ �߰�
				}
				//N toString : NŸ���� String�ڷ������� ��ȯ�Ѵ�. 
				return k38_sb.toString();
			}
		} else {	//k38_str�� ���̸� ���� ��ȯ
			return "";
		}
	}
}//class