import axiose from '@/utils/axiose'

let base = '/customer';

export const getCustomerOne = params => { return axiose.get(`${base}/findOne`, { params: params }).then(res => res.data); };

export const getCustomerListPage = params => { return axiose.get(`${base}/findList`, { params: params }).then(res => res.data); };

export const removeCustomer = params => { return axiose.post(`${base}/delete`, params).then(res => res.data); };

export const batchRemoveCustomer = params => { return axiose.post(`${base}/delete`, params).then(res => res.data); };

export const editCustomer = params => { return axiose.post(`${base}/update`, params).then(res => res.data); };

export const addCustomer = params => { return axiose.post(`${base}/insert`, params).then(res => res.data); };

export const getCustomerNameList = params => { return axiose.get(`${base}/findNameList`, { params: params }).then(res => res.data); };