import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysOrderProduct } from 'app/shared/model/sys-order-product.model';
import { SysOrderProductService } from './sys-order-product.service';
import { SysOrderProductComponent } from './sys-order-product.component';
import { SysOrderProductDetailComponent } from './sys-order-product-detail.component';
import { SysOrderProductUpdateComponent } from './sys-order-product-update.component';
import { SysOrderProductDeletePopupComponent } from './sys-order-product-delete-dialog.component';
import { ISysOrderProduct } from 'app/shared/model/sys-order-product.model';

@Injectable({ providedIn: 'root' })
export class SysOrderProductResolve implements Resolve<ISysOrderProduct> {
    constructor(private service: SysOrderProductService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysOrderProduct> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysOrderProduct>) => response.ok),
                map((sysOrderProduct: HttpResponse<SysOrderProduct>) => sysOrderProduct.body)
            );
        }
        return of(new SysOrderProduct());
    }
}

export const sysOrderProductRoute: Routes = [
    {
        path: 'sys-order-product',
        component: SysOrderProductComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysOrderProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-order-product/:id/view',
        component: SysOrderProductDetailComponent,
        resolve: {
            sysOrderProduct: SysOrderProductResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysOrderProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-order-product/new',
        component: SysOrderProductUpdateComponent,
        resolve: {
            sysOrderProduct: SysOrderProductResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysOrderProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-order-product/:id/edit',
        component: SysOrderProductUpdateComponent,
        resolve: {
            sysOrderProduct: SysOrderProductResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysOrderProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysOrderProductPopupRoute: Routes = [
    {
        path: 'sys-order-product/:id/delete',
        component: SysOrderProductDeletePopupComponent,
        resolve: {
            sysOrderProduct: SysOrderProductResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysOrderProduct.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
