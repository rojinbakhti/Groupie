import React from 'react';

export interface ContextInterface {
  username: string | null;
  login? : (username: string) => void;
  logout? : () => void;
}

export default React.createContext<ContextInterface>({
  username: null
});
