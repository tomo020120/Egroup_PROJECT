package bean;
import java.io.Serializable;

public class HoldWordBean implements Serializable{
	private String HoldWord;
	private String HoldSortNo;
	public String getHoldWord() {
		return HoldWord;
	}
	public void setHoldWord(String holdWord) {
		HoldWord = holdWord;
	}
	public String getHoldSortNo() {
		return HoldSortNo;
	}
	public void setHoldSortNo(String holdSortNo) {
		HoldSortNo = holdSortNo;
	}
	
}
