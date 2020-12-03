package org.kamiblue.botkt.commands

import org.kamiblue.botkt.*
import org.kamiblue.botkt.utils.MessageSendUtils.normal
import org.kamiblue.botkt.utils.MessageSendUtils.success
import org.kamiblue.botkt.utils.StringUtils.flat

object TopicCommand : Command("topic") {
    init {
        literal("set") {
            greedyString("topic") {
                doesLaterIfHas(PermissionTypes.MANAGE_CHANNELS) { context ->
                    var setTopic: String = context arg "topic"
                    setTopic = setTopic.flat(1024)

                    message.serverChannel?.edit {
                        topic = setTopic
                    }

                    message.success("Set Channel Topic to `$setTopic`!")
                }
            }

        }
        doesLater {
            val topic = message.serverChannel?.topic
            message.normal(if (topic.isNullOrEmpty()) "No topic set!" else topic, "#" + message.serverChannel?.name)
        }
    }
}