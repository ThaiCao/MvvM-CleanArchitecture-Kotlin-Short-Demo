package com.example.mydemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mydemo.common.FragmentParams
import com.example.mydemo.databinding.FragmentSecondBinding
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieDetailFragmentParam(
    var name: String? = "",
    var id: Long = 0,
    var posterPath: String? = "",
    var title: String? = "",
    var profilePath: String? = "",
    var voteAverage: Double? = 0.0,
) : FragmentParams

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}