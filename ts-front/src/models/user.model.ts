
export interface UserLogin {
  username: string;
  password: string;
}

export interface User {
  name: string;
  lastname: string;
  username: string;
  password: string;
}

export type UserResponse = {
  name: string;
  lastname: string;
  username: string;
}