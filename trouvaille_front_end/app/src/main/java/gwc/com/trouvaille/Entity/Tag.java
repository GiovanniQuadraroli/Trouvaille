package gwc.com.trouvaille.Entity;

public class Tag {

    public Tag(String tag){
        this.tag = tag;
    }

    private String tag;

    private String image;

    private boolean isSelected;

    public String toString(){
        return tag;
    }

    public void selectTag(){isSelected=!isSelected;}

    public boolean isSelected(){return isSelected;}
}
