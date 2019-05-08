/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysCouponUserUpdateComponent } from 'app/entities/sys-coupon-user/sys-coupon-user-update.component';
import { SysCouponUserService } from 'app/entities/sys-coupon-user/sys-coupon-user.service';
import { SysCouponUser } from 'app/shared/model/sys-coupon-user.model';

describe('Component Tests', () => {
    describe('SysCouponUser Management Update Component', () => {
        let comp: SysCouponUserUpdateComponent;
        let fixture: ComponentFixture<SysCouponUserUpdateComponent>;
        let service: SysCouponUserService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCouponUserUpdateComponent]
            })
                .overrideTemplate(SysCouponUserUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysCouponUserUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysCouponUserService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysCouponUser(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysCouponUser = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysCouponUser();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysCouponUser = entity;
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
