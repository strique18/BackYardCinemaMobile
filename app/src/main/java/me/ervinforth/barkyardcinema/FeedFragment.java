package me.ervinforth.barkyardcinema;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class FeedFragment extends Fragment {

    private ArrayList<Post> list = new ArrayList<>();
    private FeedAdapter adapter;
    private RecyclerView feedRecyclerView;

    public FeedFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        feedRecyclerView = view.findViewById(R.id.feed_list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        feedRecyclerView.setLayoutManager(manager);
        adapter = new FeedAdapter(list);
        feedRecyclerView.setAdapter(adapter);
        test();
    }

    private void test() {
        Post testPost = new Post();

        testPost.setDateTime(LocalDateTime.now());
        testPost.setType("text");
        testPost.setTextContent("A flashback shows the death of Guy's parents when he was a kid. " +
                "As they drown in tar, they tell him to find somewhere called 'tomorrow'. He " +
                "goes on a long journey to find it and in the process, meets a young Belt before " +
                "taking him along for the ride.\n" +
                "Some time after the events of the first movie, the Croods (along with Guy " +
                "and their pets Chunky and Douglas) are still searching for a place to settle " +
                "down at all while surviving many dangerous creatures along the way. Grug is " +
                "repeatedly annoyed at Eep and Guy's blossoming romance and it gets worse when" +
                " he overhears them thinking about settling down together somewhere else away " +
                "from the Croods. As Grug walks off in anger, he soon comes across a giant wall " +
                "and leads the whole pack to them. When they bust inside, they see that the wall " +
                "covers a huge land full of fruits and streams and they believe they have finally" +
                " found a place to call their home. They are soon caught in a net and are released" +
                " by the owners of the land, a couple called the Bettermans, Phil and Hope. When" +
                " the Bettermans meet Guy, they reveal that they know each other from Guy's late" +
                " parents.");
        testPost.setSummary(testPost.getTextContent().substring(0,100));

        Post testPostImage = new Post();
        testPostImage.setDateTime(LocalDateTime.now());
        testPostImage.setType("image");
        testPostImage.setSummary("Diana must contend with a work colleague and businessman, " +
                "whose desire for extreme wealth sends the world down a path of destruction, " +
                "after an ancient artifact that grants wishes goes missing.");
        testPostImage.setImageContent("https://m.media-amazon.com/images/M/MV5BYmNmM2RmMGEtNGYxYy00MGEwLThhOWQtNzkwNGRhNjFlODRmXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_FMjpg_UX600_.jpg");

        Post testPostVideo = new Post();
        testPostVideo.setDateTime(LocalDateTime.now());
        testPostVideo.setType("video");
        testPostVideo.setSummary("Wanting to lead an honest life, a notorious bank robber turns " +
                "himself in, only to be double-crossed by two ruthless FBI agents.");
        testPostVideo.setVideoContent("https://firebasestorage.googleapis.com/v0/b/backyard-cinema-app.appspot.com/o/1434659607842-pgv4ql-1612753217197.mp4?alt=media&token=81cc45ce-b0bc-4917-b2bb-af25da3ce907");

        list.add(testPost);
        list.add(testPostImage);
        list.add(testPostVideo);
        adapter.notifyDataSetChanged();
    }
}