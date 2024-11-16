import { UseApiCall, User, UserLogin, UserResponse } from "@/models";
import { axiosInstance } from '@/services';
import { loadAbort } from "@/utilities";

export const login = (credentials: UserLogin): UseApiCall<UserResponse> => {
  const controller = loadAbort();
  return {
    call: axiosInstance.post<UserResponse>('users/auth/login', credentials, {
      signal: controller.signal,
    }),
    controller,
  };
};

export const signUp = (user: User): UseApiCall<UserResponse> => {
  const controller = loadAbort();
  return {
    call: axiosInstance.post<UserResponse>('users', user, {
      signal: controller.signal,
    }),
    controller,
  };
};