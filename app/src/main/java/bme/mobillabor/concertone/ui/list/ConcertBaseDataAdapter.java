package bme.mobillabor.concertone.ui.list;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bme.mobillabor.concertone.R;
import bme.mobillabor.concertone.model.ConcertBaseData;
import bme.mobillabor.concertone.ui.details.ConcertDetailsActivity;
import bme.mobillabor.concertone.ui.upsert.ConcertUpsertActivity;

public final class ConcertBaseDataAdapter extends RecyclerView.Adapter<ConcertBaseDataAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView imMusicType;
        public final TextView tvArtist;
        public final TextView tvDateLocation;
        public final TextView tvPrice;

        public final ImageView ibDelete;
        public final ImageView ibEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imMusicType = itemView.findViewById(R.id.list_item_image);
            tvArtist = itemView.findViewById(R.id.list_item_artist);
            tvDateLocation = itemView.findViewById(R.id.list_item_date_location);
            tvPrice = itemView.findViewById(R.id.list_item_price);
            ibDelete = itemView.findViewById(R.id.list_item_delete);
            ibEdit = itemView.findViewById(R.id.list_item_edit);
        }
    }

    private final List<ConcertBaseData> items;
    private final ConcertListActivity owner;

    public ConcertBaseDataAdapter(List<ConcertBaseData> items, ConcertListActivity owner) {
        this.items = items;
        this.owner = owner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                                      .inflate(R.layout.listitem_concert, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ConcertBaseData item = items.get(i);

        viewHolder.imMusicType.setImageResource(getImageResourceId(item.getGenre()));

        viewHolder.tvArtist.setText(item.getArtist());
        viewHolder.tvArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(owner, ConcertDetailsActivity.class);
                intent.putExtra(ConcertDetailsActivity.ID_KEY, item.getId());
                owner.startActivity(intent);
            }
        });
        viewHolder.tvPrice.setText(item.getTicketPrice().toString() + " Ft");
        viewHolder.tvDateLocation.setText(ellipseText(item.getDate().substring(5, 10) + " " + item.getLocation(), 20));

        viewHolder.ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                owner.concertListPresenter.delete(item.getId());
            }
        });

        viewHolder.ibEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(owner, ConcertUpsertActivity.class);
                intent.putExtra(ConcertUpsertActivity.ID_KEY, item.getId());
                intent.putExtra(ConcertUpsertActivity.IS_EDIT_KEY, true);
                owner.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private int getImageResourceId(String genre) {
        if (genre == null) {
            return R.drawable.img_music_general;
        }
        if (genre.toUpperCase().contains("POP")) {
            return R.drawable.img_music_pop;
        }
        if (genre.toUpperCase().contains("RAP")) {
            return R.drawable.img_music_rap;
        }
        if (genre.toUpperCase().contains("DISCO")) {
            return R.drawable.img_music_disco;
        }
        if (genre.toUpperCase().contains("ROCK") || genre.toUpperCase().contains("METAL")) {
            return R.drawable.img_musis_rock;
        }
        if (genre.toUpperCase().contains("CLASSICAL")) {
            return R.drawable.img_music_clasical;
        }

        return R.drawable.img_music_general;
    }

    private String ellipseText(String source, int size) {
        if (source == null || source.length() <= size) {
            return  source;
        }
        return source.substring(0, size) + "…";
    }
}
