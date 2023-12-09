package com.example.androidadvanced_mobileafternoon.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidadvanced_mobileafternoon.adapter.JsonApiRetrofitAdapter
import com.example.androidadvanced_mobileafternoon.databinding.FragmentApiJsonRetrofitBinding
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler

class ApiJsonRetrofitFragment : Fragment() {

    private lateinit var binding: FragmentApiJsonRetrofitBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentApiJsonRetrofitBinding.inflate(layoutInflater,container,false)

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvListData.layoutManager = layoutManager
        binding.rvListData.setHasFixedSize(true)
        val itemDecoration = DividerItemDecoration(requireActivity(),layoutManager.orientation)
        binding.rvListData.addItemDecoration(itemDecoration)

        getListData()

        return binding.root
    }

    private fun getListData() {
        binding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://jsonplaceholder.typicode.com/users"
        client.get(url, object :AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                binding.progressBar.visibility = View.INVISIBLE
                val listDataUser = ArrayList<String>()
                val result = String(responseBody)
                Log.d(TAG, result )
                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        val nama = jsonObject.getString("name")
                        val email = jsonObject.getString("email")
                        listDataUser.add("\n$nama\n - $email\n")
                    }
                    val adapter = JsonApiRetrofitAdapter(listDataUser)
                    adapter.notifyDataSetChanged()
                    binding.rvListData.adapter = adapter
                }catch (e:Exception){
                    Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                binding.progressBar.visibility = View.VISIBLE
                val errorMessage = when(statusCode){
                    400 -> "$statusCode: Bad request"
                    401 -> "$statusCode: Unauthorized"
                    403 -> "$statusCode: Forbidden"
                    404 -> "$statusCode: Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object{
        private val TAG = ApiJsonRetrofitFragment::class.java.simpleName
    }
}