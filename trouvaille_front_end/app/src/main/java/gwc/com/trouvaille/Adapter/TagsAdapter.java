package gwc.com.trouvaille.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import gwc.com.trouvaille.Entity.Tag;
import gwc.com.trouvaille.R;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.ViewHolder> {

    private ArrayList<Tag> tags;
    private ArrayList<Tag> selectedTag;
    private Context context;

    public TagsAdapter(Context context, ArrayList tags){
        this.tags = tags;
        this.context = context;
        selectedTag = new ArrayList<>();
    }

    public ArrayList<Tag> getSelectedTags(){
        return selectedTag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate (R.layout.tag_card,viewGroup, false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull TagsAdapter.ViewHolder viewHolder, int i) {
        final Tag tag = tags.get(i);
        viewHolder.name.setText(tag.toString());
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tag.isSelected()){
                    selectedTag.remove(tag);
                }
                else selectedTag.add(tag);
                tag.selectTag();

            }
        });
        viewHolder.checkBox.setChecked(tag.isSelected());
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private ImageView image;

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public ImageView getImage() {
            return image;
        }

        public void setImage(ImageView image) {
            this.image = image;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        private CheckBox checkBox;

        public ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.tag_name);
            image = itemView.findViewById(R.id.tag_image);
            checkBox = itemView.findViewById(R.id.tag_selected);
        }
    }
}
