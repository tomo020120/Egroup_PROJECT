package cmd.authentication;

public class CreditCardSecurityCodeAuthentication {
	private static String createSecurityCode(String creditCardNo) {
		double conversionCardNo = Double.parseDouble(creditCardNo.substring(0,4));

		int result = (int)(Math.floor(((conversionCardNo * 9)/12)*18));

		String securityCode = Integer.valueOf(result).toString().substring(0,3);

		System.out.println("生成セキュリティコード  :" + securityCode);

		return securityCode;
	}

	public static boolean isCorrectSecurityCode(String creditCardNo,String inputSecurityCode) {
		boolean flag = false;

		String correctSecurityCode = createSecurityCode(creditCardNo);

		if(correctSecurityCode.equals(inputSecurityCode)) {
			flag = true;
		}

		return flag;
	}
}
