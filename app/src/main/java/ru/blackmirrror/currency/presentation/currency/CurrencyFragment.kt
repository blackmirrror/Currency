package ru.blackmirrror.currency.presentation.currency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blackmirrror.currency.databinding.FragmentCurrencyBinding
import java.util.Date

class CurrencyFragment : Fragment() {

    private lateinit var binding: FragmentCurrencyBinding
    private val viewModel by viewModel<CurrencyViewModel>()

    private lateinit var currencyAdapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleRecycler()
        setFields()
        observeData()
    }

    private fun handleRecycler() {
        currencyAdapter = CurrencyAdapter()
        binding.rvCurrency.adapter = currencyAdapter
    }

    private fun setFields() {
        binding.tvCurrentDate.text = TextFormatter.formatCurrentDate(Date())

        binding.ivReload.setOnClickListener {
            viewModel.checkConnection()
        }
    }

    private fun observeData() {
        viewModel.currency.observe(viewLifecycleOwner) {
            if (it != null) {
                currencyAdapter.submitList(it)
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.pb.visibility.apply {
                if (it) View.VISIBLE
                else View.GONE
            }
        }
        viewModel.dateLoading.observe(viewLifecycleOwner) {
            binding.tvLoadDate.text = TextFormatter.formatLoadDate(it)
        }
    }
}