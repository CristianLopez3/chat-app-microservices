
export interface UserLogin {
  username: string;
  password: string;
}

export interface User {
  userId: string;
  name: string;
  lastname: string;
  username: string;
  password: string;
}

export type UserResponse = {
  userId: string;
  name: string;
  lastname: string;
  username: string;
  selected?: boolean;
}