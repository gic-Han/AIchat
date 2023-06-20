package com.example.aichat.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aichat.R
import com.example.aichat.model.Message

class MessageAdapter(messageList: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder?>() {
    var messageList: List<Message>

    init {
        this.messageList = messageList
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.chat_item, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message: Message = messageList[position]
        if (message.sentBy.equals(Message.SENT_BY_ME)) {
            holder.leftChatView.visibility = View.GONE
            holder.rightChatView.visibility = View.VISIBLE
            holder.rightChatTv.text = message.message
        } else {
            holder.rightChatView.visibility = View.GONE
            holder.leftChatView.visibility = View.VISIBLE
            holder.leftChatTv.text = message.message
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }
    // 뷰홀더 부분을 그냥 위에서 바인딩 해도 될듯
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var leftChatView: LinearLayout
        var rightChatView: LinearLayout
        var leftChatTv: TextView
        var rightChatTv: TextView

        init {
            leftChatView = itemView.findViewById(R.id.left_chat_view)
            rightChatView = itemView.findViewById(R.id.right_chat_view)
            leftChatTv = itemView.findViewById(R.id.left_chat_tv)
            rightChatTv = itemView.findViewById(R.id.right_chat_tv)
        }
    }
}