package task3;

public class Sentence {
	private int SentenceID, RowId,Skip,score;
	private String Sentence,Subject,Pridicate;
	public int getSkip() {
		return Skip;
	}
	public void setSkip(int skip) {
		Skip = skip;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getPridicate() {
		return Pridicate;
	}
	public void setPridicate(String pridicate) {
		Pridicate = pridicate;
	}
	public int getSentenceID() {
		return SentenceID;
	}
	public void setSentenceID(int sentenceID) {
		SentenceID = sentenceID;
	}
	public int getRowId() {
		return RowId;
	}
	public void setRowId(int rowId) {
		RowId = rowId;
	}
	public String getSentence() {
		return Sentence;
	}
	public void setSentence(String sentence) {
		Sentence = sentence;
	}
	public void SkipIncrement(){
		Skip++;
	}
}