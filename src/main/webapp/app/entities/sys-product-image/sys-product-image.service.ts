import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysProductImage } from 'app/shared/model/sys-product-image.model';

type EntityResponseType = HttpResponse<ISysProductImage>;
type EntityArrayResponseType = HttpResponse<ISysProductImage[]>;

@Injectable({ providedIn: 'root' })
export class SysProductImageService {
    public resourceUrl = SERVER_API_URL + 'api/sys-product-images';

    constructor(protected http: HttpClient) {}

    create(sysProductImage: ISysProductImage): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysProductImage);
        return this.http
            .post<ISysProductImage>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysProductImage: ISysProductImage): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysProductImage);
        return this.http
            .put<ISysProductImage>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysProductImage>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysProductImage[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysProductImage: ISysProductImage): ISysProductImage {
        const copy: ISysProductImage = Object.assign({}, sysProductImage, {
            createTime:
                sysProductImage.createTime != null && sysProductImage.createTime.isValid() ? sysProductImage.createTime.toJSON() : null,
            updateTime:
                sysProductImage.updateTime != null && sysProductImage.updateTime.isValid() ? sysProductImage.updateTime.toJSON() : null
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
            res.body.forEach((sysProductImage: ISysProductImage) => {
                sysProductImage.createTime = sysProductImage.createTime != null ? moment(sysProductImage.createTime) : null;
                sysProductImage.updateTime = sysProductImage.updateTime != null ? moment(sysProductImage.updateTime) : null;
            });
        }
        return res;
    }
}
