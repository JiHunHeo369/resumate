'use client';

import { Create } from '@/screens/create/index';
import { use } from 'react';

export default function Page({
  params,
}: {
  params: Promise<{ id: string }> | { id: string };
}) {
  const resolvedParams = 'then' in params ? use(params) : params;
  return (
    <div>
      <Create resumeId={parseInt(resolvedParams.id)} />
    </div>
  );
}

