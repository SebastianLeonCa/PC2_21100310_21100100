package dev.sebastianleon.firebase.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import dev.sebastianleon.firebase.R
import dev.sebastianleon.firebase.adapter.ResumenAdapter
import dev.sebastianleon.firebase.model.ResumenModel

class ResumenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_resumen, container, false)
        val db = FirebaseFirestore.getInstance()
        val rvResumen: RecyclerView = view.findViewById<RecyclerView>(R.id.rvResumen)
        var lstResumen: List<ResumenModel>

        db.collection("resumen")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }

                lstResumen = value!!.documents.map { document ->
                    ResumenModel(
                        document["ingresos"].toString(),
                        document["costos"].toString()
                    )
                }
                rvResumen.adapter = ResumenAdapter(lstResumen)
                rvResumen.layoutManager = LinearLayoutManager(requireContext())

            }
        return view
    }
}