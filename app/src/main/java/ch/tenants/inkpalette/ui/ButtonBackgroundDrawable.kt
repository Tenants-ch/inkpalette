package ch.tenants.inkpalette.ui

import android.graphics.*
import android.graphics.drawable.Drawable


class ButtonBackgroundDrawable(
    val startColor: Int,
    val endColor: Int,
    val borderWidth: Int,
    val borderRadius: Int
) :
    Drawable() {

    var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var mBorderWidth: Float = borderWidth.toFloat()
    var mBorderRadius: Float = borderRadius.toFloat()
    var mRect = RectF()
    var mPath = Path()

    init {
        mPaint.style = Paint.Style.FILL

        mPath.fillType = Path.FillType.EVEN_ODD

    }

    override fun onBoundsChange(bounds: Rect?) {
        super.onBoundsChange(bounds)
        mPath.reset();

        // out rect
        if (bounds != null) {
            mRect.set(
                bounds.left + mBorderWidth,
                bounds.top + mBorderWidth,
                bounds.right - mBorderWidth,
                bounds.bottom - mBorderWidth
            )
        };
        mPath.addRoundRect(mRect, mBorderRadius, mBorderRadius, Path.Direction.CW);

        // inner rect
        if (bounds != null) {
            mRect.set((bounds.left + 20).toFloat(),
                (bounds.top + 20).toFloat(), (bounds.right - 20).toFloat(), (bounds.bottom - 20).toFloat()
            )
        };
        mPath.addRoundRect(mRect, mBorderRadius, mBorderRadius, Path.Direction.CW);
    }

    override fun draw(canvas: Canvas) {
        // kind of strock
        mPaint.shader = LinearGradient(
            0f,
            0f,
            0f,
            100f,
            startColor,
            endColor,
            Shader.TileMode.MIRROR
        )
        canvas.drawPath(mPath, mPaint);
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha; }

    override fun setColorFilter(p0: ColorFilter?) {
        mPaint.colorFilter = p0;
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT; }
}