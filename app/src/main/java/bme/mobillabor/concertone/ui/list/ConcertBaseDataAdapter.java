package bme.mobillabor.concertone.ui.list;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import bme.mobillabor.concertone.R;
import bme.mobillabor.concertone.model.ConcertBaseData;
import bme.mobillabor.concertone.ui.details.ConcertDetailsActivity;
import bme.mobillabor.concertone.ui.upsert.ConcertUpsertActivity;

public final class ConcertBaseDataAdapter extends RecyclerView.Adapter<ConcertBaseDataAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView tvArtist;
        public final ImageButton ibDelete;
        public final ImageButton ibEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvArtist = itemView.findViewById(R.id.list_item_artist);
            ibDelete = itemView.findViewById(R.id.list_item_delete);
            ibEdit = itemView.findViewById(R.id.list_item_edit);
            // TODO: implement remaining properties
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

        viewHolder.tvArtist.setText(item.getArtist());
        viewHolder.tvArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(owner, ConcertDetailsActivity.class);
                intent.putExtra(ConcertDetailsActivity.ID_KEY, item.getId());
                owner.startActivity(intent);
            }
        });

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

        // TODO: set remaining view holder properties
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
