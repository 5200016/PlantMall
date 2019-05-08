import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysCouponProduct } from 'app/shared/model/sys-coupon-product.model';

type EntityResponseType = HttpResponse<ISysCouponProduct>;
type EntityArrayResponseType = HttpResponse<ISysCouponProduct[]>;

@Injectable({ providedIn: 'root' })
export class SysCouponProductService {
    public resourceUrl = SERVER_API_URL + 'api/sys-coupon-products';

    constructor(protected http: HttpClient) {}

    create(sysCouponProduct: ISysCouponProduct): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysCouponProduct);
        return this.http
            .post<ISysCouponProduct>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysCouponProduct: ISysCouponProduct): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysCouponProduct);
        return this.http
            .put<ISysCouponProduct>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysCouponProduct>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysCouponProduct[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysCouponProduct: ISysCouponProduct): ISysCouponProduct {
        const copy: ISysCouponProduct = Object.assign({}, sysCouponProduct, {
            createTime:
                sysCouponProduct.createTime != null && sysCouponProduct.createTime.isValid() ? sysCouponProduct.createTime.toJSON() : null,
            updateTime:
                sysCouponProduct.updateTime != null && sysCouponProduct.updateTime.isValid() ? sysCouponProduct.updateTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
            res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((sysCouponProduct: ISysCouponProduct) => {
                sysCouponProduct.createTime = sysCouponProduct.createTime != null ? moment(sysCouponProduct.createTime) : null;
                sysCouponProduct.updateTime = sysCouponProduct.updateTime != null ? moment(sysCouponProduct.updateTime) : null;
            });
        }
        return res;
    }
}
