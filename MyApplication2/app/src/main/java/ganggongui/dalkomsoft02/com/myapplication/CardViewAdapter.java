package ganggongui.dalkomsoft02.com.myapplication;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganggongui on 15. 1. 28..
 */
public abstract class CardViewAdapter extends GenericBaseAdapter {

    private Context context;

    private ArrayList<CardData> arrayList = new ArrayList<CardData>();


    public CardViewAdapter(Context context, SpeedScrollListener scrollListener, List<? extends Object> items) {
        super(context, scrollListener, items);
        this.context = context;
    }

    public void addItem(String cardtext) {
        CardData data = new CardData();

        data.CardText = cardtext;


        arrayList.add(data);


    }


    @Override
    protected void defineInterpolator() {
        interpolator = new DecelerateInterpolator();
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return arrayList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link android.view.LayoutInflater#inflate(int, android.view.ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Viewholder viewholder;

        if (convertView == null) {
            viewholder = new Viewholder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.list_item, null);

            viewholder.textView = (TextView) convertView.findViewById(R.id.TV_title);

            viewholder.cardView = (CardView) convertView.findViewById(R.id.card_view);

            viewholder.cardView.setCardElevation(40);

            convertView.setTag(viewholder);

        } else {
            viewholder = (Viewholder) convertView.getTag();
        }

        CardData cardData = arrayList.get(position);

        viewholder.textView.setText(cardData.CardText);

        viewholder.cardView.setCardElevation(40);

        return convertView;
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    public void reverse() {


    }


    @Override
    public View getAnimatedView(int position, View convertView, ViewGroup parent) {
        v = getRowView(position, convertView, parent);

        if (v != null && !positionsMapper.get(position) && position > previousPostition) {
            speed = scrollListener.getSpeed();

            animDuration = (((int) speed) == 0) ? ANIM_DEFAULT_SPEED : (long) (1 / speed * 15000);

            if (animDuration > ANIM_DEFAULT_SPEED)
                animDuration = ANIM_DEFAULT_SPEED;

            previousPostition = position;

            v.setTranslationX(0.0F);
            v.setTranslationY(height);
            v.setRotationX(45.0F);
            v.setScaleX(0.7F);
            v.setScaleY(0.55F);

            ViewPropertyAnimator localViewPropertyAnimator =
                    v.animate().rotationX(0.0F).rotationY(0.0F).translationX(0).translationY(0).setDuration(animDuration).scaleX(
                            1.0F).scaleY(1.0F).setInterpolator(interpolator);

            localViewPropertyAnimator.setStartDelay(0).start();
            positionsMapper.put(position, true);
        }
        return v;

    }

    protected abstract View getRowView(int position, View convertView, ViewGroup parent);


}
