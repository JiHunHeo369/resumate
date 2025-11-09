import { LoginForm } from '@/features/auth/ui/LoginForm';
import Image from "next/image";
import bgImage from './assests/resume.jpg';

export default function LoginPage() {
  return (
    <div className="flex h-screen">
      <Image src={bgImage} className="min-w-3/5 max-w-3/5 h-screen" alt=""/>
      <div className="min-w-2/5 max-w-2/5 bg-white flex flex-col items-center justify-center">
        <LoginForm />
      </div>
    </div>
  );
}