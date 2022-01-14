package cmd.RandomNumberGenerator;

import java.text.DecimalFormat;
import java.util.Random;

public abstract class CreateRandomNumber { // パスワード変更時のワンタイムパスワードとして乱数生成するクラス。
	public static String getSixDegitsNumber() {
		String sixDegits = new DecimalFormat("000000").format(new Random().nextInt(999999)); // NumberFormatのサブクラスのDecimalFormatで生成桁数を指定し、0から999999未満で乱数生成

		return sixDegits;
	}
}
