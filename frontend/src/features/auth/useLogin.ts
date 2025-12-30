'use client';

import { loginApi } from '../../shared/api/auth/loginApi';
import { User } from '@/entities/user/model';

export function useLogin() {
  const login = async (email: string, password: string): Promise<User> => {
    try {
      const user = await loginApi(email, password);
      sessionStorage.setItem('jwtToken', user.token || '');
      return user;
    } catch (e) {
      throw e;
    }
  };

  return { login };
}
