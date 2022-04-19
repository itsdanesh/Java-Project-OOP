package businessLogic;

public class Review {

    private String comment;
    private int grade;

    public Review(String comment,int grade){
        this.comment=comment;
        this.grade=grade;
    }

    public void setComment(String comment){this.comment=comment;}
    public String getComment(){return this.comment;}
    public void setGrade(int grade){this.grade=grade;}
    public int getGrade(){return this.grade;}

    public String toString(){
        String value="Grade: "+this.grade+"."+this.comment;
        return value;
    }
}