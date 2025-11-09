'use client';

import { useState } from 'react';
import { loginApi } from '../lib/loginApi';
import { User } from '@/entities/user/model';

export function useLogin() {
  const [loading, setLoading] = useState(false);

  const login = async (email: string, password: string): Promise<User> => {
    setLoading(true);
    try {
      const user = await loginApi(email, password);
      return user;
    } catch (e) {
      throw e;
    }
  };

  return { login, loading };
}