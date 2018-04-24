package batrand.android.popupreminder.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yalantis.beamazingtoday.interfaces.AnimationType;
import com.yalantis.beamazingtoday.interfaces.BatModel;
import com.yalantis.beamazingtoday.listeners.BatListener;
import com.yalantis.beamazingtoday.listeners.OnItemClickListener;
import com.yalantis.beamazingtoday.listeners.OnOutsideClickedListener;
import com.yalantis.beamazingtoday.ui.adapter.BatAdapter;
import com.yalantis.beamazingtoday.ui.animator.BatItemAnimator;
import com.yalantis.beamazingtoday.ui.callback.BatCallback;
import com.yalantis.beamazingtoday.ui.widget.BatRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import batrand.android.popupreminder.R;
import batrand.android.popupreminder.services.formatter.IFormatService;
import batrand.android.popupreminder.services.popup.IPopupService;
import batrand.android.popupreminder.services.reminder.IReminderService;
import batrand.android.popupreminder.views.bases.BaseFragment;
import batrand.android.popupreminder.views.callbacks.IMainCabActivity;

import static batrand.android.popupreminder.services.injection.SingletonsInjector.getInjector;

public class NotificationsFragment3 extends BaseFragment  implements BatListener, OnItemClickListener, OnOutsideClickedListener {

    @Override protected int getLayoutId() { return R.layout.fragment_notifications; }

    private int myInt;
    private MyListner listener;

    public interface MyListner {
//        void doThings(int listenerInt);
    }
    public NotificationsFragment3() {} // Required empty public constructor

    public static NotificationsFragment3 newInstance(IMainCabActivity cabHost) {
        NotificationsFragment3 fragment = new NotificationsFragment3();
        fragment.setCabHost(cabHost);
//        fragment.myInt = myInt;
//        fragment.listener = listener;
        return fragment;
    }

    @Inject IPopupService mPopupService;
    @Inject IReminderService mReminderService;
    @Inject IFormatService mFormatter;

//    private CoordinatorLayout mCoordinator;
//    private EmptyableRecyclerView mRecycler;
////    private NotificationsAdapter2 mAdapter;
    private BatAdapter mAdapter;
//    private SwipeRefreshLayout mRefresh;
    private List<BatModel> mGoals;
    private BatRecyclerView mRecyclerView;
    private BatItemAnimator mAnimator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
//        getInjector().inject(this);

//        mAdapter=null;
//        view.findViewById(R.id.minimalize).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(getActivity(), ToDoActivity.class);
////                myIntent.putExtra("key", value); //Optional parameters
//                getActivity().startActivity(myIntent);
//            }
//        });

//        ((TextView) view.findViewById(R.id.text_title)).setTypeface(TypefaceUtil.getAvenirTypeface(this.getContext()));

        mRecyclerView =(BatRecyclerView) view.findViewById(R.id.bat_recycler_view_notifications);
        mAnimator = new BatItemAnimator();
        mRecyclerView.getView().setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecyclerView.getView().setAdapter(mAdapter = new BatAdapter(mGoals = new ArrayList<BatModel>() {{
            add(new Goal("first"));
            add(new Goal("second"));
            add(new Goal("third"));
            add(new Goal("fourth"));
            add(new Goal("fifth"));
            add(new Goal("sixth"));
            add(new Goal("seventh"));
            add(new Goal("eighth"));
            add(new Goal("ninth"));
            add(new Goal("tenth"));
        }}, this, mAnimator).setOnItemClickListener(this).setOnOutsideClickListener(this));


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new BatCallback(this));
        itemTouchHelper.attachToRecyclerView(mRecyclerView.getView());
        mRecyclerView.getView().setItemAnimator(mAnimator);
        mRecyclerView.setAddItemListener(this);

        RelativeLayout layout=view.findViewById(R.id.view_notifications_all);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.revertAnimation();
            }
        });
//        view.setOnTouchListener(new View.OnTouchListener() {
//            public boolean onTouch(View v, MotionEvent event) {
//
//                if(event.getAction() == MotionEvent.ACTION_DOWN){
//                    System.out.print("test");
//                }
//                return true;
//            }
//        });
//        mCoordinator = (CoordinatorLayout) view.findViewById(R.id.notifications_coordinator);
//
//        mRecycler = (EmptyableRecyclerView) view.findViewById(R.id.notifications_recycler);
//        mAdapter = new NotificationsAdapter2(getActivity(), mItemLongClickHandler);
//        mRecycler.setAdapter(mAdapter);
//        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        mRecycler.setEmptyView(view.findViewById(R.id.notifications_recycler_empty));
//
//        mRefresh = (SwipeRefreshLayout) view.findViewById(R.id.notifications_recycler_swipe);
//        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mAdapter.reload(new ReloadDoneCallback() {
//                   @Override
//                    public void onReloadDone() {
//                       mRefresh.setRefreshing(false);
//                   }
//                });
//            }
//        });

        return view;
    }


//    private BroadcastReceiver mReloadReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if(!intent.getAction().equals(mReminderService.getConfigs().getChangeAvailableBroadcastString()))
//                return;
//            mAdapter.reload(null);
//        }
//    };

//    @Override
//    public void onPause() {
//        super.onPause();
//        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mReloadReceiver);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        LocalBroadcastManager.getInstance(getActivity())
//                .registerReceiver(mReloadReceiver,
//                        new IntentFilter(mReminderService.getConfigs().getChangeAvailableBroadcastString())
//                );
//    }
//
//    private IOnRecyclerItemLongClick mItemLongClickHandler = new IOnRecyclerItemLongClick() {
//        @Override
//        public void onItemLongClick(int itemPosition) {
//            getCabHost().startCab(mCabCallback);
//
//            // Start CAB visuals in adapter
//            mAdapter.setCabMode(mRecycler, true);
//        }
//    };

//    private ActionMode.Callback mCabCallback = new ActionMode.Callback() {
//        @Override
//        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//            mode.getMenuInflater().inflate(R.menu.notifications_menu, menu);
//            return true;
//        }
//
//        @Override
//        public boolean onPrepareActionMode(ActionMode mode, Menu menu) { return false; }
//
//        @Override
//        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//            int selectionCount;
//            switch(item.getItemId()) {
//                case R.id.notifications_menu_selectall:
//                    mAdapter.selectAll(mRecycler);
//                    return true;
//                case R.id.notifications_menu_mark_seen:
//                    selectionCount = mAdapter.selectionCount(mRecycler);
//                    if(selectionCount < 1) return true;
//
//                    mAdapter.markSeenSelected(mRecycler);
//                    mAdapter.reload(null);
//                    mode.finish();
//
//                    if(selectionCount > 1) showSnackbar(mCoordinator, getString(R.string.notifications_marked_seen));
//                    else showSnackbar(mCoordinator, getString(R.string.notification_marked_seen));
//                    return true;
//                case R.id.notifications_menu_delete:
//                    selectionCount = mAdapter.selectionCount(mRecycler);
//                    if(selectionCount < 1) return true;
//
//                    mAdapter.deleteSelected(mRecycler);
//                    mAdapter.reload(null);
//                    mode.finish();
//
//                    if(selectionCount > 1) showSnackbar(mCoordinator, getString(R.string.notifications_deleted));
//                    else showSnackbar(mCoordinator, getString(R.string.notification_deleted));
//                    return true;
//                default: return false;
//            }
//        }
//
//        @Override
//        public void onDestroyActionMode(ActionMode mode) {
//            mAdapter.setCabMode(mRecycler, false);
//            getCabHost().onCabStopped();
//        }
//    };

    @Override
    public void add(String text) {
        mGoals.add(0, new Goal(text));
        mAdapter.notify(AnimationType.ADD, 0);
    }

    @Override
    public void delete(int position) {
        mGoals.remove(position);
        mAdapter.notify(AnimationType.REMOVE, position);
    }

    @Override
    public void move(int from, int to) {
        if (from >= 0 && to >= 0) {
            mAnimator.setPosition(to);
            BatModel model = mGoals.get(from);
            mGoals.remove(model);
            mGoals.add(to, model);
            mAdapter.notify(AnimationType.MOVE, from, to);

            if (from == 0 || to == 0) {
                mRecyclerView.getView().scrollToPosition(Math.min(from, to));
            }
        }
    }

    @Override
    public void onClick(BatModel item, int position) {
        Toast.makeText(this.getActivity(), item.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOutsideClicked() {
        mRecyclerView.revertAnimation();
    }
}
