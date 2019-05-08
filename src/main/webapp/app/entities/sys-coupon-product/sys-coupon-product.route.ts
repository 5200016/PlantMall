import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysCouponProduct } from 'app/shared/model/sys-coupon-product.model';
import { SysCouponProductService } from './sys-coupon-product.service';
import { SysCouponProductComponent } from './sys-coupon-product.component';
import { SysCouponProductDetailComponent } from './sys-coupon-product-detail.component';
import { SysCouponProductUpdateComponent } from './sys-coupon-product-update.component';
import { SysCouponProductDeletePopupComponent } from './sys-coupon-product-delete-dialog.component';
import { ISysCouponProduct } from 'app/shared/model/sys-coupon-product.model';

@Injectable({ providedIn: 'root' })
export class SysCouponProductResolve implements Resolve<ISysCouponProduct> {
    constructor(private service: SysCouponProductService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysCouponProduct> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysCouponProduct>) => response.ok),
                map((sysCouponProduct: HttpResponse<SysCouponProduct>) => sysCouponProduct.body)
            );
        }
        return of(new SysCouponProduct());
    }
}

export const sysCouponProductRoute: Routes = [
    {
        path: 'sys-coupon-product',
        component: SysCouponProductComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCouponProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-coupon-product/:id/view',
        component: SysCouponProductDetailComponent,
        resolve: {
            sysCouponProduct: SysCouponProductResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCouponProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-coupon-product/new',
        component: SysCouponProductUpdateComponent,
        resolve: {
            sysCouponProduct: SysCouponProductResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCouponProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-coupon-product/:id/edit',
        component: SysCouponProductUpdateComponent,
        resolve: {
            sysCouponProduct: SysCouponProductResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCouponProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysCouponProductPopupRoute: Routes = [
    {
        path: 'sys-coupon-product/:id/delete',
        component: SysCouponProductDeletePopupComponent,
        resolve: {
            sysCouponProduct: SysCouponProductResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysCouponProduct.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
