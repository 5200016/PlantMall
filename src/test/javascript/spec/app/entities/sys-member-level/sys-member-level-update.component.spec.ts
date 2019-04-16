/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysMemberLevelUpdateComponent } from 'app/entities/sys-member-level/sys-member-level-update.component';
import { SysMemberLevelService } from 'app/entities/sys-member-level/sys-member-level.service';
import { SysMemberLevel } from 'app/shared/model/sys-member-level.model';

describe('Component Tests', () => {
    describe('SysMemberLevel Management Update Component', () => {
        let comp: SysMemberLevelUpdateComponent;
        let fixture: ComponentFixture<SysMemberLevelUpdateComponent>;
        let service: SysMemberLevelService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysMemberLevelUpdateComponent]
            })
                .overrideTemplate(SysMemberLevelUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysMemberLevelUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysMemberLevelService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysMemberLevel(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysMemberLevel = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysMemberLevel();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysMemberLevel = entity;
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
