
function buildUrl(url: string, params?: Record<string, any>) {
  if (!params) return url;
  const query = new URLSearchParams(params).toString();
  return `${url}?${query}`;
}

async function baseFetch(input: RequestInfo, init?: RequestInit): Promise<Response> {
  const accessToken = sessionStorage.getItem('jwtToken');
  const baseUrl = process.env.NEXT_PUBLIC_API_BASE_URL || '';

  const response = await fetch(baseUrl + input, {
    ...init,
    headers: {
      ...(init?.headers || {}),
      Authorization: accessToken ? `Bearer ${accessToken}` : '',
    },
    credentials: 'include',
  });

  if (response.status === 401) {
    try {
      const refreshRes = await fetch(baseUrl + '/api/users/reissue', {
        method: 'POST',
        credentials: 'include',
        headers: { 'Content-Type': 'application/json' },
      });

      if (!refreshRes.ok) throw new Error('토큰 재발급 실패');

      const { token: newAccessToken } = await refreshRes.json();
      sessionStorage.setItem('jwtToken', newAccessToken);

      return fetch(input, {
        ...init,
        headers: {
          ...(init?.headers || {}),
          Authorization: `Bearer ${newAccessToken}`,
        },
        credentials: 'include',
      });
    } catch (err) {
      window.location.href = '/login';
    }
  }

  return response;
}

export async function get(url: string, params?: Record<string, any>, options?: RequestInit) {
  const fullUrl = buildUrl(url, params);
  return baseFetch(fullUrl, { ...options, method: 'GET' });
}

export async function post(url: string, body?: any, options?: RequestInit) {
  return baseFetch(url, {
    ...options,
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      ...(options?.headers || {}),
    },
    body: JSON.stringify(body),
  });
}

export async function put(url: string, body?: any, options?: RequestInit) {
  return baseFetch(url, {
    ...options,
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      ...(options?.headers || {}),
    },
    body: JSON.stringify(body),
  });
}

export async function del(url: string, params?: Record<string, any>, options?: RequestInit) {
  const fullUrl = buildUrl(url, params);
  return baseFetch(fullUrl, { ...options, method: 'DELETE' });
}