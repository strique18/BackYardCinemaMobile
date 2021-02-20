package me.ervinforth.barkyardcinema;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Post> posts;

    public FeedAdapter(ArrayList<Post> posts) {
        this.posts = posts;
    }

    @Override
    public int getItemViewType(int position) {
        Post current = posts.get(position);
        switch (current.getType()) {
            case "video":
                return 1;
            case "image":
                return 2;
            default:
                return 0;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == 2) {
            view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.post_view_image, parent, false);

            return new PostImageViewHolder(view);
        }

        if (viewType == 1) {
            view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.post_view_video, parent, false);

            return new PostVideoViewHolder(view);
        }

        view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.post_view, parent, false);

        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Post current = posts.get(position);
        Resources res = holder.itemView.getResources();
        Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.backyard_logo, null);

        if (holder instanceof PostViewHolder) {
            PostViewHolder viewHolder = (PostViewHolder)holder;
            viewHolder.avatarView.setImageDrawable(drawable);
            viewHolder.textView.setText(current.getTextContent());
            viewHolder.dateTimeView.setText(current.getDateTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        }

        if (holder instanceof PostVideoViewHolder) {
            PostVideoViewHolder viewHolder = (PostVideoViewHolder)holder;
            viewHolder.avatarView.setImageDrawable(drawable);
            viewHolder.textView.setText(current.getSummary());
            viewHolder.dateTimeView.setText(current.getDateTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

            MediaItem mediaItem = MediaItem.fromUri(current.getVideoContent());
            SimpleExoPlayer player = new SimpleExoPlayer.Builder(viewHolder.videoView.getContext()).build();
            viewHolder.videoView.setPlayer(player);
            player.setMediaItem(mediaItem);
            player.prepare();
        }

        if (holder instanceof PostImageViewHolder) {
            PostImageViewHolder viewHolder = (PostImageViewHolder)holder;
            viewHolder.textView.setText(current.getSummary());
            viewHolder.dateTimeView.setText(current.getDateTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
            viewHolder.avatarView.setImageDrawable(drawable);
            Glide.with(viewHolder.imageView.getContext()).load(current.getImageContent()).into(viewHolder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        CircularImageView avatarView;
        TextView dateTimeView;
        TextView textView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarView = itemView.findViewById(R.id.avatar);
            dateTimeView = itemView.findViewById(R.id.post_timestamp);
            textView = itemView.findViewById(R.id.post_text);
        }
    }

    static class PostImageViewHolder extends RecyclerView.ViewHolder {
        CircularImageView avatarView;
        TextView dateTimeView;
        TextView textView;
        ImageView imageView;

        public PostImageViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarView = itemView.findViewById(R.id.avatar);
            dateTimeView = itemView.findViewById(R.id.post_timestamp);
            textView = itemView.findViewById(R.id.post_summary);
            imageView = itemView.findViewById(R.id.post_image);
        }
    }

    static class PostVideoViewHolder extends RecyclerView.ViewHolder {
        CircularImageView avatarView;
        TextView dateTimeView;
        TextView textView;
        PlayerView videoView;

        public PostVideoViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarView = itemView.findViewById(R.id.avatar);
            dateTimeView = itemView.findViewById(R.id.post_timestamp);
            textView = itemView.findViewById(R.id.post_summary);
            videoView = itemView.findViewById(R.id.post_video);
        }
    }
}
