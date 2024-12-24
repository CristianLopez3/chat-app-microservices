import { UseApiCall } from "@/models";
import { Conversation, ConversationDTO } from "@/models/conversations.model";
import { axiosInstance } from '@/services';
import { loadAbort } from "@/utilities";

export const createConversation = (conversation: ConversationDTO): UseApiCall<Conversation> => {
	const controller = loadAbort();
	return {
		call: axiosInstance.post<Conversation>('/conversations', conversation, {
			signal: controller.signal,
		}),
		controller,
	};
};

export const getUserConversationsById = (userId: string): UseApiCall<Conversation[]> => {
	const controller = loadAbort();
	return {
		call: axiosInstance.get<Conversation[]>(`/conversations`, {
			signal: controller.signal,
			params: {
				user: userId
			}
		}),
		controller,
	};
};

