package me.ervinforth.barkyardcinema;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    ArrayList<Movie> mMovies;

    public MovieAdapter(ArrayList<Movie> movies) {
        mMovies = movies;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MovieAdapter.MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieHolder holder, int position) {
        // 1. Retrieve a movie object based on the position in the list
        Movie movie = mMovies.get(position);
        // 2. Take the details the movie object and pass to the MovieHolder object
        holder.mTitle.setText(movie.getTitle());
        Glide.with(holder.mPoster.getContext()).load(movie.getPoster()).into(holder.mPoster);
        holder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("MovieAdapter", "Movie Item was clicked.");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    static class MovieHolder extends RecyclerView.ViewHolder {

        TextView mTitle;
        ImageView mPoster;
        ConstraintLayout mItem;

        public MovieHolder(View view) {
            super(view);
            mTitle = view.findViewById(R.id.movie_item_title);
            mPoster = view.findViewById(R.id.movie_item_image);
            mItem = view.findViewById(R.id.movie_item);
        }
    }
}
