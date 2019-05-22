package com.android.task.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.task.R
import com.android.task.di.Injectable
import com.android.task.model.User
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_user_detail.*
import kotlinx.android.synthetic.main.user_detail.view.*

/**
 * A fragment representing a single User detail screen.
 * This fragment is either contained in a [UserListActivity]
 * in two-pane mode (on tablets) or a [UserDetailActivity]
 * on handsets.
 */
class UserDetailFragment : Fragment(), Injectable, OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM)) {
                item = it.getParcelable(ARG_ITEM)
                activity?.toolbar_layout?.title = item?.name
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.user_detail, container, false)

        item?.let {
            rootView.user_detail.text = it.email
        }

        (this.childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)?.let {
            it.getMapAsync(this)}



        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item that this fragment
         * represents.
         */
        const val ARG_ITEM = "item"
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        val location = LatLng(item?.address?.geo?.lat?.toDouble()!!, item?.address?.geo?.lng?.toDouble()!!)
        mMap.addMarker(MarkerOptions().position(location).title((item as User).name))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }
}
