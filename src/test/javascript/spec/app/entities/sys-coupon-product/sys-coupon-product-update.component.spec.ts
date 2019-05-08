/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysCouponProductUpdateComponent } from 'app/entities/sys-coupon-product/sys-coupon-product-update.component';
import { SysCouponProductService } from 'app/entities/sys-coupon-product/sys-coupon-product.service';
import { SysCouponProduct } from 'app/shared/model/sys-coupon-product.model';

describe('Component Tests', () => {
    describe('SysCouponProduct Management Update Component', () => {
        let comp: SysCouponProductUpdateComponent;
        let fixture: ComponentFixture<SysCouponProductUpdateComponent>;
        let service: SysCouponProductService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCouponProductUpdateComponent]
            })
                .overrideTemplate(SysCouponProductUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysCouponProductUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysCouponProductService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysCouponProduct(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysCouponProduct = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysCouponProduct();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysCouponProduct = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
