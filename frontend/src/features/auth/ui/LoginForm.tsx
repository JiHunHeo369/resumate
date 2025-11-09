'use client';

import { Button, TextField } from '@mui/material';
import { useState } from "react";
import Swal from 'sweetalert2';
import { useLogin } from '../model/useLogin';

export function LoginForm() {
  const [loginId, setLoginId] = useState('');
  const [password, setPassword] = useState('');
  const { login } = useLogin();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await login(loginId, password);
      Swal.fire('로그인 성공!', 'success');
    } catch {
      Swal.fire('로그인 실패', '아이디 또는 비밀번호를 확인하세요', 'error');
    }
  };


  return (
    <>
      <h1>RESUMATE</h1>
      <form className="flex justify-center w-full pb-[100px]">
        <div className="grid grid-cols-1 gap-[10px] w-3/5">
          <TextField
            label="아이디"
            type="text"
            value={loginId}
            onChange={(e) => setLoginId(e.target.value)}
            fullWidth
            required
          />

          <TextField
            label="비밀번호"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            fullWidth
            required
          />


          <Button
            type="submit"
            variant="contained"
            fullWidth
            className="bg-blue-600 hover:bg-blue-700 text-white py-2 rounded-md disabled:opacity-50"
            onClick={handleSubmit}
          >
            로그인
          </Button>
        </div>
      </form>
    </>
  );
}