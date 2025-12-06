package com.example.ftaks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ProfilFragment : Fragment() {

    var username: String? = null
    var password: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString("username")
            password = it.getString("password")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profil, container, false)

        val tvUser = view.findViewById<TextView>(R.id.et_username)
        val tvPass = view.findViewById<TextView>(R.id.et_password)

        tvUser.text = username
        tvPass.text = password

        return view
    }
    companion object {
        fun newInstance(username: String, password: String) =
            ProfilFragment().apply {
                arguments = Bundle().apply {
                    putString("username", username)
                    putString("password", password)
                }
            }
    }
}