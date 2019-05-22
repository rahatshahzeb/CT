package com.android.task.ui.users

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.android.task.R
import com.android.task.databinding.ActivityUserListBinding
import com.android.task.model.User

import com.android.task.ui.users.dummy.DummyContent
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_user_list.*
import kotlinx.android.synthetic.main.user_list_content.view.*
import kotlinx.android.synthetic.main.user_list.*
import javax.inject.Inject

/**
 * An activity representing a list of User. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [UserDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class UserListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var userViewModel: UserViewModel

    private var userList: List<User> = ArrayList()

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list)

        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)

        binding.viewModel = userViewModel

        binding.lifecycleOwner = this

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (user_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView(user_list)

        subscribeObservers()
    }

    private fun subscribeObservers() {
        subscribeUserListObserver()
    }

    private fun subscribeUserListObserver() {
        userViewModel.userList.observe(this, Observer {
            if (it != null) {
                userList = it
                setupRecyclerView(user_list)
            }
        })
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, userList, twoPane)
    }

    class SimpleItemRecyclerViewAdapter(
        private val parentActivity: UserListActivity,
        private val values: List<User>,
        private val twoPane: Boolean
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as User
                if (twoPane) {
                    val fragment = UserDetailFragment().apply {
                        arguments = Bundle().apply {
                            putParcelable(UserDetailFragment.ARG_ITEM, item)
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.user_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, UserDetailActivity::class.java).apply {
                        putExtra(UserDetailFragment.ARG_ITEM, item)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.nameView.text = item.name
            holder.emailView.text = item.email
            holder.phoneView.text = item.phone

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nameView: TextView = view.name
            val emailView: TextView = view.email
            val phoneView: TextView = view.phone
        }
    }
}
