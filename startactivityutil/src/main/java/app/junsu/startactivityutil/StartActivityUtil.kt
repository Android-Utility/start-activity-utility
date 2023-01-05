package app.junsu.startactivityutil

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.*

/**
 * @author junsuPark
 * StartActivity extensions utility class.
 */
object StartActivityUtil {

    /**
     * @since 0.0.1
     * @param context a context which calls extension
     * @param to a class which is destination of extension
     * @param flag list of intent filters
     * private startActivity method, base of whole StartActivity extensions.
     */
    private fun <T : Activity> Activity.startActivity(
        context: Context, to: Class<out T>,
        flag: List<Int> = listOf(FLAG_ACTIVITY_SINGLE_TOP),
    ) {
        startActivity(Intent(
            /* packageContext = */
            context,
            /* cls = */
            to,
        ).apply {
            flag.forEach {
                this.addFlags(
                    /* flags = */
                    it,
                )
            }
        })
    }

    /**
     * @since 0.0.1
     * @param context a context which calls extension
     * @param to a class which is destination of extension
     * @see Activity.startActivity
     * default startActivity method, which let context's activity maintain single-top.
     */
    fun <T : Activity> Activity.startActivity(
        context: Context,
        to: Class<out T>,
    ) {
        startActivity(
            context = context,
            to = to,
            flag = listOf(FLAG_ACTIVITY_SINGLE_TOP),
        )
    }

    /**
     * @since 0.0.1
     * @param context a context which calls extension
     * @param to a class which is destination of extension
     * @param flag list of intent filters
     * @see Activity.startActivity
     * a startActivity method, which finishes context's activity.
     */
    fun <T : Activity> Activity.startActivityFinishingCurrentActivity(
        context: Context, to: Class<out T>,
        flag: List<Int> = listOf(FLAG_ACTIVITY_SINGLE_TOP),
    ) {
        startActivity(
            context = context,
            to = to,
            flag = flag,
        )
        finish()
    }

    /**
     * @since 0.0.1
     * @param context a context which calls extension
     * @param to a class which is destination of extension
     * @see Activity.startActivity
     * a startActivity method, which finishes context's activity and removes its backstack.
     */
    fun <T : Activity> Activity.startActivityRemovingBackStack(
        context: Context,
        to: Class<out T>,
    ) {
        startActivityFinishingCurrentActivity(
            context = context,
            to = to,
            flag = listOf(FLAG_ACTIVITY_CLEAR_TASK, FLAG_ACTIVITY_NEW_TASK),
        )
    }
}