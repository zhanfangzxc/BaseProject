package gift.makemoney.adapters;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import gift.makemoney.R;
import gift.makemoney.model.Unsplash;

/**
 * Created by malk on 2017/3/8.
 */

public class UnsplashAdapterItem implements AdapterItem<Unsplash> {
    @BindView(R.id.image) ImageView mImageView;
    private final ColorDrawable[] shotLoadingPlaceholders;

    public UnsplashAdapterItem(Context context) {
        int[] placeholderColors = context.getResources().getIntArray(R.array.loading_placeholders_dark);
        shotLoadingPlaceholders = new ColorDrawable[placeholderColors.length];
        for (int i = 0; i < placeholderColors.length; i++) {
            shotLoadingPlaceholders[i] = new ColorDrawable(placeholderColors[i]);
        }
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.item_unsplash;
    }

    @Override
    public void bindViews(View root) {
        ButterKnife.bind(this, root);
    }

    @Override
    public void bindData(Context context, int position, Unsplash unsplash, int viewType) {
        Glide.with(context).load(unsplash.getUrls().getSmall())
                .centerCrop()
                .placeholder(shotLoadingPlaceholders[position % shotLoadingPlaceholders.length])
                .into(mImageView);
    }
}
