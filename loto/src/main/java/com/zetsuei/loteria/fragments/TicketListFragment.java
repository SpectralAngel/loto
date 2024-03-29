package com.zetsuei.loteria.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;


import butterknife.ButterKnife;
import butterknife.InjectView;
import com.zetsuei.loteria.ModelLoader;
import com.zetsuei.loteria.R;
import com.zetsuei.loteria.adapters.TicketAdapter;
import com.zetsuei.loteria.model.Ticket;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p />
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p />
 * Activities containing this fragment MUST implement the Callbacks
 * interface.
 */
public class TicketListFragment extends Fragment implements AbsListView.OnItemClickListener {

    TicketLoader ticketLoader;

    private OnFragmentInteractionListener fragmentInteractionListener;

    /**
     * The fragment's ListView/GridView.
     */
    @InjectView(android.R.id.list) ListView listView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private TicketAdapter ticketAdapter;

    // TODO: Rename and change types of parameters
    public static TicketListFragment newInstance() {
        TicketListFragment fragment = new TicketListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TicketListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Change Adapter to display your content
        ticketAdapter = new TicketAdapter(getActivity());
        ticketLoader = new TicketLoader(getActivity());
        getActivity().getSupportLoaderManager().initLoader(0, null, ticketLoader);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        ButterKnife.inject(this, view);

        // Set the adapter
        listView.setAdapter(ticketAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            fragmentInteractionListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentInteractionListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != fragmentInteractionListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            //fragmentInteractionListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = listView.getEmptyView();

        if (emptyText instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
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
        public void onFragmentInteraction(String id);
    }

    class TicketLoader implements LoaderManager.LoaderCallbacks<List<Ticket>>
    {
        Context context;
        public TicketLoader(Context context)
        {
            this.context = context;
        }

        @Override
        public Loader<List<Ticket>> onCreateLoader(int id, Bundle args)
        {
            return new ModelLoader<>(context, Ticket.class);
        }

        @Override
        public void onLoadFinished(Loader<List<Ticket>> loader, List<Ticket> data)
        {
            ticketAdapter.clear();
            ticketAdapter.addAll(data);
            ticketAdapter.notifyDataSetChanged();
        }

        @Override
        public void onLoaderReset(Loader<List<Ticket>> loader)
        {
            ticketAdapter.clear();
            ticketAdapter.notifyDataSetChanged();
        }
    }

}
