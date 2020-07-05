package adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kithnkin.yummytreat.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bean.DessertBean;

/**
 * Created by Prince on 07-06-2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MasonryView>{

    private Context context;
    private ArrayList<DessertBean> arrayList;

    public HomeAdapter(Context context, ArrayList<DessertBean> dessertBeanArrayList) {
        this.context = context;
        this.arrayList = dessertBeanArrayList;
    }

    public HomeAdapter(Context context) {
        this.context = context;
    }

    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recent, parent, false);
        MasonryView masonryView = new MasonryView(layoutView);
        return masonryView;
    }

    public void onBindViewHolder(MasonryView holder, final int position)
    {
        try {
            String imageurl = arrayList.get(position).getImage().toString();
            Picasso.with(context).load(imageurl).into(holder.imageView);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        holder.textView.setText(arrayList.get(position).getTitle());
          /*  holder.cardView.setOnClickListener(new View.OnClickListener()
            {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(context, DescriptionActivity.class);
                intent.putExtra("image",arrayList.get(position).getImage());
                intent.putExtra("title",arrayList.get(position).getTitle());
                intent.putExtra("description",arrayList.get(position).getDescription());
                intent.putExtra("category",arrayList.get(position).getCategory());
                context.startActivity(intent);
            }
        });*/
           /*holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DescriptionActivity.class);
                intent.putExtra("image",arrayList.get(position).getImage());
                intent.putExtra("title",arrayList.get(position).getTitle());
                intent.putExtra("description",arrayList.get(position).getDescription());
                intent.putExtra("category",arrayList.get(position).getCategory());
                context.startActivity(intent);
            }
        });*/
    }

    public int getItemCount()
    {
        return arrayList.size();
    }

    class MasonryView extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MasonryView(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imgDessert);
            textView = (TextView) itemView.findViewById(R.id.txtTitle);
        }
    }
}
