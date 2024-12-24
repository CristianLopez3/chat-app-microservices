import { UseApiCall } from "@/models";
import { MessageResponse } from "@/models/message.model";
import { axiosInstance } from '@/services';
import { loadAbort } from "@/utilities";

export const getMessagesByConversationId = (id: string): UseApiCall<MessageResponse[]> => {
  const controller = loadAbort();
  console.log("Calling get messages by conversation id");
  return {
    call: axiosInstance.get<MessageResponse[]>(`messages/${id}`, {
      signal: controller.signal,
    }),
    controller,
  };
};