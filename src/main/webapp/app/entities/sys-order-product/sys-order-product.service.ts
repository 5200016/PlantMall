import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysOrderProduct } from 'app/shared/model/sys-order-product.model';

type EntityResponseType = HttpResponse<ISysOrderProduct>;
type EntityArrayResponseType = HttpResponse<ISysOrderProduct[]>;

@Injectable({ providedIn: 'root' })
export class SysOrderProductService {
    public resourceUrl = SERVER_API_URL + 'api/sys-order-products';

    constructor(protected http: HttpClient) {}

    create(sysOrderProduct: ISysOrderProduct): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysOrderProduct);
        return this.http
            .post<ISysOrderProduct>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysOrderProduct: ISysOrderProduct): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysOrderProduct);
        return this.http
            .put<ISysOrderProduct>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysOrderProduct>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysOrderProduct[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysOrderProduct: ISysOrderProduct): ISysOrderProduct {
        const copy: ISysOrderProduct = Object.assign({}, sysOrderProduct, {
            createTime:
                sysOrderProduct.createTime != null && sysOrderProduct.createTime.isValid() ? sysOrderProduct.createTime.toJSON() : null,
            updateTime:
                sysOrderProduct.updateTime != null && sysOrderProduct.updateTime.isValid() ? sysOrderProduct.updateTime.toJSON() : null
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
            res.body.forEach((sysOrderProduct: ISysOrderProduct) => {
                sysOrderProduct.createTime = sysOrderProduct.createTime != null ? moment(sysOrderProduct.createTime) : null;
                sysOrderProduct.updateTime = sysOrderProduct.updateTime != null ? moment(sysOrderProduct.updateTime) : null;
            });
        }
        return res;
    }
}
