package com.rb.shoppinglist;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShoppingListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShoppingListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingListFragment extends Fragment {

    private static final String TAG = ShoppingListFragment.class.getSimpleName();
    private OnFragmentInteractionListener mListener;
    private View optionsLayout;
    private Animation fabOpenAnimation;
    private Animation fabCloseAnimation;
    private boolean isFabMenuOpen;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager layoutManager;
    private View parentView;
    private boolean isListView;

    public ShoppingListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShoppingListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingListFragment newInstance() {
        ShoppingListFragment fragment = new ShoppingListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentView = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        init();

        return parentView;
    }

    private void init() {
        getAnimations();
        fab = (FloatingActionButton) parentView.findViewById(R.id.fab);
        optionsLayout = parentView.findViewById(R.id.options_layout);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFabMenuOpen)
                    collapseFabMenu();
                else
                    expandFabMenu();
            }
        });

        recyclerView = (RecyclerView) parentView.findViewById(R.id.list);

        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        ListAdapter listAdapter = new ListAdapter(getContext(), ItemDatabase.getShoppingList());
        recyclerView.setAdapter(listAdapter);
        isListView = true;

        setHasOptionsMenu(true);
    }

    private void getAnimations() {

        fabOpenAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);

        fabCloseAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);

    }


    private void expandFabMenu() {
        optionsLayout.setVisibility(View.VISIBLE);
        ViewCompat.animate(fab).rotation(45.0F).withLayer().setDuration(300).setInterpolator(new OvershootInterpolator(10.0F)).start();
        optionsLayout.startAnimation(fabOpenAnimation);

        isFabMenuOpen = true;

    }

    private void collapseFabMenu() {
        ViewCompat.animate(fab).rotation(0.0F).withLayer().setDuration(300).setInterpolator(new OvershootInterpolator(10.0F)).start();
        optionsLayout.startAnimation(fabCloseAnimation);
        isFabMenuOpen = false;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //            throw new RuntimeException(context.toString()
            //                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_grid) {
            Log.d(TAG, "Grid");

            if (isListView) {
                layoutManager.setSpanCount(2);
                item.setIcon(R.drawable.ic_menu_camera);
                item.setTitle("Show as list");
                isListView = false;
            } else {
                layoutManager.setSpanCount(1);
                item.setIcon(R.drawable.ic_menu_gallery);
                item.setTitle("Show as grid");
                isListView = true;
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
